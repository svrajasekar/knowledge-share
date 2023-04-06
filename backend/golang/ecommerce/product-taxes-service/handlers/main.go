package handlers

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"

	"net/http"

	"product-taxes-service/models"

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

func (h handler) GetAllTaxes(w http.ResponseWriter, r *http.Request) {
	var taxes []models.Tax

	if result := h.DB.Find(&taxes); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(taxes)
}

func (h handler) GetTax(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Find Tax by Id
	var tax models.Tax

	if result := h.DB.First(&tax, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(tax)
}

func (h handler) AddTax(w http.ResponseWriter, r *http.Request) {
	// Read to request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var tax models.Tax
	json.Unmarshal(body, &tax)

	// Append to the Categories table
	if result := h.DB.Create(&tax); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Send a 201 created response
	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode("Created")
}

func (h handler) UpdateTax(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Read request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var updatedTax models.Tax
	json.Unmarshal(body, &updatedTax)

	var tax models.Tax

	if result := h.DB.First(&tax, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	tax.Name = updatedTax.Name
	tax.Description = updatedTax.Description
	tax.TaxPercentage = updatedTax.TaxPercentage

	h.DB.Save(&tax)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Updated")
}

func (h handler) DeleteTax(w http.ResponseWriter, r *http.Request) {
	// Read the dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	var tax models.Tax

	// Find the Tax by Id
	if result := h.DB.First(&tax, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Delete that Categpru
	h.DB.Delete(&tax)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Deleted")
}
