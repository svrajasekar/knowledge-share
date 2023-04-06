package models

import (
	"gorm.io/gorm"
)

type Tax struct {
	gorm.Model
	Name          string `json:"name"`
	Description   string `json:"description"`
	TaxPercentage int    `json:"taxpercentage"`
}
