package handlers

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"

	"net/http"

	"product-manufacturers-service/models"

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

func (h handler) GetAllManufacturers(w http.ResponseWriter, r *http.Request) {
	var manufacturers []models.Manufacturer

	if result := h.DB.Find(&manufacturers); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(manufacturers)
}

func (h handler) GetManufacturer(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Find Tax by Id
	var manufacturer models.Manufacturer

	if result := h.DB.First(&manufacturer, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(manufacturer)
}

func (h handler) AddManufacturer(w http.ResponseWriter, r *http.Request) {
	// Read to request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var manufacturer models.Manufacturer
	json.Unmarshal(body, &manufacturer)

	// Append to the Categories table
	if result := h.DB.Create(&manufacturer); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Send a 201 created response
	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode("Created")
}

func (h handler) UpdateManufacturer(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Read request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var updatedManufacturer models.Manufacturer
	json.Unmarshal(body, &updatedManufacturer)

	var manufacturer models.Manufacturer

	if result := h.DB.First(&manufacturer, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	manufacturer.Name = updatedManufacturer.Name
	manufacturer.StreetAddress = updatedManufacturer.StreetAddress
	manufacturer.City = updatedManufacturer.City
	manufacturer.State = updatedManufacturer.State
	manufacturer.Zip = updatedManufacturer.Zip
	manufacturer.Country = updatedManufacturer.Country
	manufacturer.PhoneNumbers = updatedManufacturer.PhoneNumbers
	manufacturer.FascimileNumbers = updatedManufacturer.FascimileNumbers
	manufacturer.EmailAddresses = updatedManufacturer.EmailAddresses

	h.DB.Save(&manufacturer)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Updated")
}

func (h handler) DeleteManufacturer(w http.ResponseWriter, r *http.Request) {
	// Read the dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	var manufacturer models.Manufacturer

	// Find the Tax by Id
	if result := h.DB.First(&manufacturer, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Delete that Categpru
	h.DB.Delete(&manufacturer)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Deleted")
}
