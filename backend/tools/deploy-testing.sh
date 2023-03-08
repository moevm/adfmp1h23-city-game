#!/usr/bin/env bash

echo ${DOCKER_OAUTH} | docker login --username oauth --password-stdin cr.yandex

docker build . \
  --build-arg "HOST=${HOST}" \
  --build-arg "PORT=${PORT}" \
  --build-arg "APP_KEYS=${APP_KEYS}" \
  --build-arg "API_TOKEN_SALT=${API_TOKEN_SALT}" \
  --build-arg "ADMIN_JWT_SECRET=${ADMIN_JWT_SECRET}" \
  --build-arg "DATABASE_HOST=${DATABASE_HOST}" \
  --build-arg "DATABASE_NAME=${DATABASE_NAME}" \
  --build-arg "DATABASE_USERNAME=${DATABASE_USERNAME}" \
  --build-arg "DATABASE_PASSWORD=${DATABASE_PASSWORD}" \
  --build-arg "DATABASE_CERT=${DATABASE_CERT}" \
  --build-arg "JWT_SECRET=${JWT_SECRET}" \
  --tag "cr.yandex/${REGISTRY_ID}/${DOCKER_IMAGE_URL}"

docker push "cr.yandex/${REGISTRY_ID}/${DOCKER_IMAGE_URL}"
