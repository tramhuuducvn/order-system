# Service Bus Name Space
resource "azurerm_servicebus_namespace" "order_sys_svc_bus_namespace" {
  name                = var.servicebus_namespace_name
  location            = var.location
  resource_group_name = var.resource_group_name
  sku                 = "Basic"

  tags = {
    create_by = "terraform"
  }
}

# Inventory Queue
resource "azurerm_servicebus_queue" "inventory_queue" {
  name                  = var.inventory_queue_name
  namespace_id          = azurerm_servicebus_namespace.order_sys_svc_bus_namespace.id
  max_size_in_megabytes = 1024
  partitioning_enabled  = false
}

# Delivery Queue
resource "azurerm_servicebus_queue" "delivery_queue" {
  name                  = var.delivery_queue_name
  namespace_id          = azurerm_servicebus_namespace.order_sys_svc_bus_namespace.id
  max_size_in_megabytes = 1024
  partitioning_enabled  = false
}
