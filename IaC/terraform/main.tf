provider "azurerm" {
    # The "feature" block is required for AzureRM provider 2.x. 
    # If you are using version 1.x, the "features" block is not allowed.
    version = "~>2.0"
    features {}
}

provider "docker" {
    host = "tcp://localhost:2376"

    registry_auth {
      address = "registry.hub.docker.com"
      config_file = "${pathexpand("~/.docker/config.json")}"
    }
    }

terraform {
    backend "azurerm" {}
}