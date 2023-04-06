package main

import (
	"log"
	"net/http"

	"product-categories-service/db"
	"product-categories-service/handlers"

	"github.com/gorilla/mux"
)

func main() {
	DB := db.Init()
	h := handlers.New(DB)
	router := mux.NewRouter()

	router.HandleFunc("/categories", h.GetAllCategories).Methods(http.MethodGet)
	router.HandleFunc("/categories/{id}", h.GetCategory).Methods(http.MethodGet)
	router.HandleFunc("/categories", h.AddCategory).Methods(http.MethodPost)
	router.HandleFunc("/categories/{id}", h.UpdateCategory).Methods(http.MethodPut)
	router.HandleFunc("/categories/{id}", h.DeleteCategory).Methods(http.MethodDelete)

	log.Println("Product Categories API is running!")
	http.ListenAndServe(":7002", router)
}
