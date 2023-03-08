#!/usr/bin/env bash

BASEDIR=$(dirname $0)

export DOCKER_IMAGE_URL='android-strapi-testing'

. ${BASEDIR}/deploy-testing.sh

