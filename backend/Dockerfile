FROM node:16

ARG NODE_ENV=production
ARG HOST
ARG PORT
ARG APP_KEYS
ARG API_TOKEN_SALT
ARG ADMIN_JWT_SECRET
ARG DATABASE_HOST
ARG DATABASE_NAME
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD
ARG DATABASE_CERT
ARG JWT_SECRET

ENV NODE_ENV=${NODE_ENV}
ENV HOST=${HOST}
ENV PORT=${PORT}
ENV APP_KEYS=${APP_KEYS}
ENV API_TOKEN_SALT=${API_TOKEN_SALT}
ENV ADMIN_JWT_SECRET=${ADMIN_JWT_SECRET}
ENV DATABASE_HOST=${DATABASE_HOST}
ENV DATABASE_NAME=${DATABASE_NAME}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}
ENV DATABASE_CERT=${DATABASE_CERT}
ENV JWT_SECRET=${JWT_SECRET}

WORKDIR /app

COPY ./config ./config
COPY ./database ./database
COPY ./public ./public
COPY ./src ./src
COPY ./package*.json ./
COPY ./.npmrc ./.npmrc

RUN npm install

RUN npm run build
RUN npm prune --production

EXPOSE $PORT

CMD ["npm", "start"]
