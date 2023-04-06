package main

import (
	"log"
	"net/http"

	"product-manufacturers-service/db"
	"product-manufacturers-service/handlers"

	"github.com/gorilla/mux"
)

func main() {
	DB := db.Init()
	h := handlers.New(DB)
	router := mux.NewRouter()

	router.HandleFunc("/manufacturers", h.GetAllManufacturers).Methods(http.MethodGet)
	router.HandleFunc("/manufacturers/{id}", h.GetManufacturer).Methods(http.MethodGet)
	router.HandleFunc("/manufacturers", h.AddManufacturer).Methods(http.MethodPost)
	router.HandleFunc("/manufacturers/{id}", h.UpdateManufacturer).Methods(http.MethodPut)
	router.HandleFunc("/manufacturers/{id}", h.DeleteManufacturer).Methods(http.MethodDelete)

	log.Println("Product Manufacturers API is running!")
	http.ListenAndServe(":7003", router)
}
