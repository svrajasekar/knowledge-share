package main

import (
	"log"
	"net/http"

	"product-reviews-service/db"
	"product-reviews-service/handlers"

	"github.com/gorilla/mux"
)

func main() {
	DB := db.Init()
	h := handlers.New(DB)
	router := mux.NewRouter()

	router.HandleFunc("/reviews", h.GetAllReviews).Methods(http.MethodGet)
	router.HandleFunc("/reviews/{id}", h.GetReview).Methods(http.MethodGet)
	router.HandleFunc("/reviews", h.AddReview).Methods(http.MethodPost)
	router.HandleFunc("/reviews/{id}", h.UpdateReview).Methods(http.MethodPut)
	router.HandleFunc("/reviews/{id}", h.DeleteReview).Methods(http.MethodDelete)

	log.Println("Reviews Reviews API is running!")
	http.ListenAndServe(":7004", router)
}
