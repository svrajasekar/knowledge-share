package main

import (
	"log"
	"net/http"

	"product-taxes-service/db"
	"product-taxes-service/handlers"

	"github.com/gorilla/mux"
)

func main() {
	DB := db.Init()
	h := handlers.New(DB)
	router := mux.NewRouter()

	router.HandleFunc("/taxes", h.GetAllTaxes).Methods(http.MethodGet)
	router.HandleFunc("/taxes/{id}", h.GetTax).Methods(http.MethodGet)
	router.HandleFunc("/taxes", h.AddTax).Methods(http.MethodPost)
	router.HandleFunc("/taxes/{id}", h.UpdateTax).Methods(http.MethodPut)
	router.HandleFunc("/taxes/{id}", h.DeleteTax).Methods(http.MethodDelete)

	log.Println("Product Taxes API is running!")
	http.ListenAndServe(":7001", router)
}
