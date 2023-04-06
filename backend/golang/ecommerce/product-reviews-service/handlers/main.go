package handlers

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"

	"net/http"

	"product-reviews-service/models"

	"gorm.io/gorm"

	"strconv"

	"github.com/gorilla/mux"
)

type handler struct {
	DB *gorm.DB
}

func New(db *gorm.DB) handler {
	return handler{db}
}

func (h handler) GetAllReviews(w http.ResponseWriter, r *http.Request) {
	var reviews []models.Review

	if result := h.DB.Find(&reviews); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(reviews)
}

func (h handler) GetReview(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Find Review by Id
	var review models.Review

	if result := h.DB.First(&review, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(review)
}

func (h handler) AddReview(w http.ResponseWriter, r *http.Request) {
	// Read to request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var review models.Review
	json.Unmarshal(body, &review)

	// Append to the Categories table
	if result := h.DB.Create(&review); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Send a 201 created response
	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode("Created")
}

func (h handler) UpdateReview(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Read request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var updatedReview models.Review
	json.Unmarshal(body, &updatedReview)

	var review models.Review

	if result := h.DB.First(&review, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	review.ProductId = updatedReview.ProductId
	review.Comments = updatedReview.Comments
	review.Rating = updatedReview.Rating

	h.DB.Save(&review)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Updated")
}

func (h handler) DeleteReview(w http.ResponseWriter, r *http.Request) {
	// Read the dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	var review models.Review

	// Find the Tax by Id
	if result := h.DB.First(&review, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Delete that Categpru
	h.DB.Delete(&review)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Deleted")
}
