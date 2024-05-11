docker volume create upload
docker network create all-in-one

docker run -d --name postgresdb \
  -p 5432:5432 \
  -e POSTGRES_USER=user \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_DB=all-in-one \
  --network all-in-one \
  postgres:14.1-alpine

sleep 5s

docker run -d --name all-in-one \
  --network all-in-one \
  -p 8080:8080 \
  -e DB_HOST=jdbc:postgresql://postgresdb:5432/all-in-one \
  -e DB_USERNAME=user \
  -e DB_PASSWORD=secret \
  -e BASE_PATH=/workspace/photo \
  --mount source=upload,target=/workspace/photo \
  --user root:root \
  ghcr.io/fullstack-e-learning/all-in-one:210420241933

echo "Please wait, collecting the status...."
sleep 5s

docker container ps -a --format table