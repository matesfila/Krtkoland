IMAGE_NAME=krtkoland-app
IMAGE_FILE=krtkoland.tar

buildDocker:
	docker build -t $(IMAGE_NAME) .

exportDocker:
	docker save -o $(IMAGE_FILE) $(IMAGE_NAME)

all: buildDocker exportDocker
