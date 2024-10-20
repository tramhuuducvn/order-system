resource "azurerm_cosmosdb_account" "order_cosmosdb_account_name" {
  name                          = var.cosmosdb_account_name
  location                      = var.location
  resource_group_name           = var.resource_group_name
  offer_type                    = "Standard"
  kind                          = "MongoDB"
  free_tier_enabled             = true
  public_network_access_enabled = true

  capacity {
    total_throughput_limit = 1000
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
    location          = var.location
    failover_priority = 0
  }
}
