CREATE TABLE IF NOT EXISTS "users" (
	"uid" UUID NOT NULL DEFAULT gen_random_uuid(),
	"id" SERIAL NOT NULL,
	"username" VARCHAR NOT NULL,
	"full_name" VARCHAR NOT NULL,
	"email" VARCHAR NOT NULL,
	"dob" DATE NOT NULL,
	"role_id" INTEGER NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "users_credentials" (
	"id" SERIAL NOT NULL,
	"user_id" INTEGER NOT NULL,
	"provider_id" INTEGER NOT NULL,
	"hash" TEXT NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT 'now()',
    PRIMARY KEY ("user_id", "provider_id")
);
