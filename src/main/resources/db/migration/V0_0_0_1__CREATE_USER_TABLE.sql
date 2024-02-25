CREATE TABLE "users" (
    "id" uuid PRIMARY KEY,
    "name" varchar(256) NOT NULL,
    "password" varchar(256) NOT NULL,
    "email" varchar(256) NOT NULL,
    "role" varchar(256) NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL
);