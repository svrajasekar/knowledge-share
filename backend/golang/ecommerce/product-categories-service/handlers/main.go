package handlers

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"

	"net/http"

	"product-categories-service/models"

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

func (h handler) GetAllCategories(w http.ResponseWriter, r *http.Request) {
	var categories []models.Category

	if result := h.DB.Find(&categories); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(categories)
}

func (h handler) GetCategory(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Find Category by Id
	var category models.Category

	if result := h.DB.First(&category, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(category)
}

func (h handler) AddCategory(w http.ResponseWriter, r *http.Request) {
	// Read to request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var category models.Category
	json.Unmarshal(body, &category)

	// Append to the Categories table
	if result := h.DB.Create(&category); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Send a 201 created response
	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode("Created")
}

func (h handler) UpdateCategory(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Read request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var updatedCategory models.Category
	json.Unmarshal(body, &updatedCategory)

	var category models.Category

	if result := h.DB.First(&category, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	category.Name = updatedCategory.Name
	category.Description = updatedCategory.Description
	category.TaxId = updatedCategory.TaxId

	h.DB.Save(&category)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Updated")
}

func (h handler) DeleteCategory(w http.ResponseWriter, r *http.Request) {
	// Read the dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	var category models.Category

	// Find the Category by Id
	if result := h.DB.First(&category, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Delete that Categpru
	h.DB.Delete(&category)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Deleted")
}
