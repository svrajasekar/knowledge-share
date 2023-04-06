package handlers

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"

	"net/http"

	"products-service/models"

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

func (h handler) GetAllProducts(w http.ResponseWriter, r *http.Request) {
	var products []models.Product

	if result := h.DB.Find(&products); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(products)
}

func (h handler) GetProduct(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Find Tax by Id
	var product models.Product

	if result := h.DB.First(&product.ID, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(product)
}

func (h handler) AddProduct(w http.ResponseWriter, r *http.Request) {
	// Read to request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var product models.Product
	json.Unmarshal(body, &product)

	// Append to the Categories table
	if result := h.DB.Create(&product); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Send a 201 created response
	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode("Created")
}

func (h handler) UpdateProduct(w http.ResponseWriter, r *http.Request) {
	// Read dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	// Read request body
	defer r.Body.Close()
	body, err := ioutil.ReadAll(r.Body)

	if err != nil {
		log.Fatalln(err)
	}

	var updatedProduct models.Product
	json.Unmarshal(body, &updatedProduct)

	var product models.Product

	if result := h.DB.First(&product, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	product.Name = updatedProduct.Name
	product.Description = updatedProduct.Description
	product.Price = updatedProduct.Price
	product.ManufacturerId = updatedProduct.ManufacturerId
	product.CategoryId = updatedProduct.CategoryId
	product.TaxId = updatedProduct.TaxId

	h.DB.Save(&product)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Updated")
}

func (h handler) DeleteProduct(w http.ResponseWriter, r *http.Request) {
	// Read the dynamic id parameter
	vars := mux.Vars(r)
	id, _ := strconv.Atoi(vars["id"])

	var product models.Product

	// Find the Tax by Id
	if result := h.DB.First(&product, id); result.Error != nil {
		fmt.Println(result.Error)
	}

	// Delete that Categpru
	h.DB.Delete(&product)

	w.Header().Add("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode("Deleted")
}
