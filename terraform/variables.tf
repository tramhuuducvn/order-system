# Variables for Resource Group, Location, etc.
variable "subscription_id" {
  default     = "80b496a3-4e33-4584-aa49-385619bfa59c"
  description = "Subscription 1"
}

variable "location" {
  default = "East US"
}

variable "resource_group_name" {
  default = "order-system"
}

# CosmosDB
variable "cosmosdb_account_name" {
  default = "order-sys-cosmosdb-account"
}

variable "cosmosdb_name" {
  default = "order-system-cosmosdb"
}

# Service Bus Namespace
variable "servicebus_namespace_name" {
  default = "ordersyssvcbusnamespace"
}

variable "inventory_queue_name" {
  default = "inventory_queue"
}

variable "delivery_queue_name" {
  default = "delivery_queue"
}

# Container Application
variable "container_app_environment" {
  default = "order-sys-environment"
}

variable "order_svc_ctn_app_name" {
  default = "order-service-application"
}

variable "delivery_svc_ctn_app_name" {
  default = "delivery-service-application"
}

variable "inventory_svc_ctn_app_name" {
  default = "inventory-service-application"
}
