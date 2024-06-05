terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.41"
    }

    ansible = {
      version = "~> 1.3.0"
      source  = "ansible/ansible"
    }

    docker = {
      source = "kreuzwerker/docker"
      version = "~> 3.0.1"
    }
  }
  backend "s3" {
    bucket = "tfpocbucket001"
    key    = "ec2-efs/all-in-one/terraform.tfstate"
    region = "eu-north-1"
  }
}

provider "aws" {
  region = "eu-north-1"
}

provider "docker" {}