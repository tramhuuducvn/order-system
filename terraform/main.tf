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
  features {
    resource_group {
      prevent_deletion_if_contains_resources = false
    }
  }
  subscription_id = var.subscription_id # Subscription 1
}

# Resource Group
resource "azurerm_resource_group" "order_system_rg" {
  name     = var.resource_group_name
  location = var.location
  tags = {
    subscription = "sub_1_free_tier",
    create_by    = "terraform"
  }
}


resource "azurerm_container_app_environment" "order_sys_ctn_app_environment" {
  name                = var.container_app_environment
  location            = azurerm_resource_group.order_system_rg.location
  resource_group_name = azurerm_resource_group.order_system_rg.name
}

resource "azurerm_cosmosdb_account" "order_cosmosdb_account_name" {
  name                       = var.cosmosdb_account_name
  location                   = var.location
  resource_group_name        = var.resource_group_name
  offer_type                 = "Standard"
  kind                       = "MongoDB"
  automatic_failover_enabled = true

  capabilities {
    name = "EnableAggregationPipeline"
  }

  capabilities {
    name = "mongoEnableDocLevelTTL"
  }

  capabilities {
    name = "MongoDBv3.4"
  }

  capabilities {
    name = "EnableMongo"
  }

  consistency_policy {
    consistency_level       = "BoundedStaleness"
    max_interval_in_seconds = 300
    max_staleness_prefix    = 100000
  }

  geo_location {
    location          = "eastus"
    failover_priority = 0
  }
}
