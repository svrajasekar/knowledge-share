package models

import (
	"gorm.io/gorm"
)

type Manufacturer struct {
	gorm.Model
	Name             string `json:"name"`
	StreetAddress    string `json:"streetaddress"`
	City             string `json:"city"`
	State            string `json:"state"`
	Zip              string `json:"zip"`
	Country          string `json:"country"`
	PhoneNumbers     string `json:"phonenumbers"`
	FascimileNumbers string `json:"fascimilenumbers"`
	EmailAddresses   string `json:"emailaddresses"`
}
