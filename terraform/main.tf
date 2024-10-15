terraform {
  required_version = "=1.9.7"
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "4.5.0"
    }
  }
}

provider "azurerm" {
  features {}
  subscription_id = "80b496a3-4e33-4584-aa49-385619bfa59c" # Subscription 1
}

# Resource Group
resource "azurerm_resource_group" "rg" {
  name     = "the-gioi-manh-dong"
  location = "Southeast Asia"
}

resource "azurerm_resource_group" "rg" {
  name     = "the-gioi-manh-dong"
  location = "Southeast Asia"
}
