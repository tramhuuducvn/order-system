
resource "azurerm_cosmosdb_mongo_database" "order_sys_cosmosdb" {
  name                = var.cosmosdb_name
  resource_group_name = var.resource_group_name
  account_name        = var.cosmosdb_account_name
  throughput          = 1000
}
