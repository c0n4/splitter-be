test:
	docker-compose -f ./src/test/docker-compose.yml up --build --abort-on-container-exit --force-recreate
deploy:
	docker-compose -f docker-compose.yml up --remove-orphans --force-recreate