docker build -t lgaljo/rt_basketball_stats -f Dockerfile_with_maven_build .
docker tag lgaljo/rt_basketball_stats lgaljo/rt_basketball_stats:latest
docker push -a lgaljo/rt_basketball_stats