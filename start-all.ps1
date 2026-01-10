# start-all.ps1

Write-Host "Starting discovery-service..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd discovery-server; mvn spring-boot:run"

Write-Host "Starting api-gateway..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd api-gateway; mvn spring-boot:run"

Write-Host "Starting doctor-service..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd doctor-service; mvn spring-boot:run"

Write-Host "Starting appointment-service..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd appointment-service; mvn spring-boot:run"

Write-Host "All services started!"
