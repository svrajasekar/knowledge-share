package models

import (
	"gorm.io/gorm"
)

type Review struct {
	gorm.Model
	ProductId int    `json:"productid"`
	Comments  string `json:"comments"`
	Rating    int    `json:"rating"`
}
