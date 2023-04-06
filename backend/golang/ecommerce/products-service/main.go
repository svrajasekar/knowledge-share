package main

import (
	"log"
	"net/http"

	"products-service/db"
	"products-service/handlers"

	"github.com/gorilla/mux"
)

func main() {
	DB := db.Init()
	h := handlers.New(DB)
	router := mux.NewRouter()

	router.HandleFunc("/products", h.GetAllProducts).Methods(http.MethodGet)
	router.HandleFunc("/products/{id}", h.GetProduct).Methods(http.MethodGet)
	router.HandleFunc("/products", h.AddProduct).Methods(http.MethodPost)
	router.HandleFunc("/products/{id}", h.UpdateProduct).Methods(http.MethodPut)
	router.HandleFunc("/products/{id}", h.DeleteProduct).Methods(http.MethodDelete)

	log.Println("Products API is running!")
	http.ListenAndServe(":7004", router)
}
