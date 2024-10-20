resource "azurerm_container_app" "order_svc_ctn_app" {
  name                         = var.order_svc_ctn_app_name
  container_app_environment_id = azurerm_container_app_environment.order_sys_ctn_app_environment.id
  resource_group_name          = var.resource_group_name
  revision_mode                = "Single"

  ingress {
    external_enabled = true
    target_port      = 8080
    traffic_weight {
      label           = "order-svc"
      percentage      = 100
      revision_suffix = "release"
      latest_revision = true
    }
  }

  template {
    container {
      name   = "ordersvcctnapp"
      image  = "tramhuuducvn/order-service-img:latest"
      cpu    = 0.25
      memory = "0.5Gi"
    }
  }
}

resource "azurerm_container_app" "inventory_svc_ctn_app" {
  name                         = var.inventory_svc_ctn_app_name
  container_app_environment_id = azurerm_container_app_environment.order_sys_ctn_app_environment.id
  resource_group_name          = var.resource_group_name
  revision_mode                = "Single"

  ingress {
    external_enabled = true
    target_port      = 8080
    traffic_weight {
      label           = "inventory-svc"
      percentage      = 100
      revision_suffix = "release"
      latest_revision = true
    }
  }

  template {
    container {
      name   = "inventorysvcctnapp"
      image  = "tramhuuducvn/inventory-service-img:latest"
      cpu    = 0.25
      memory = "0.5Gi"
    }
  }
}

resource "azurerm_container_app" "delivery_svc_ctn_app" {
  name                         = var.delivery_svc_ctn_app_name
  container_app_environment_id = azurerm_container_app_environment.order_sys_ctn_app_environment.id
  resource_group_name          = var.resource_group_name
  revision_mode                = "Single"

  ingress {
    external_enabled = true
    target_port      = 8080
    traffic_weight {
      label           = "delivery-svc"
      percentage      = 100
      revision_suffix = "release"
      latest_revision = true
    }
  }

  template {
    container {
      name   = "deliverysvcctnapp"
      image  = "tramhuuducvn/delivery-service-img:latest"
      cpu    = 0.25
      memory = "0.5Gi"
    }
  }
}
