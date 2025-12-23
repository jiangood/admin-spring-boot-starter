cd ../
set  "tag_name=v0.0.9"

:: 测试并安装到本地
call mvnw clean install -DskipTests   -Drevision=%tag_name%

git tag -d %tag_name%

git push origin --delete %tag_name%

git tag %tag_name%

git push origin %tag_name%


