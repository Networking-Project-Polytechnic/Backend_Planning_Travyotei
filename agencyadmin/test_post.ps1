$json = Get-Content -Raw create_schedule.json
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8080/api/v1/schedules' -Method Post -Body $json -ContentType 'application/json'
    $response | ConvertTo-Json
}
catch {
    $_.ErrorDetails.Message
    $_.Exception.Message
    if ($_.Exception.Response) {
        $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
        $reader.ReadToEnd()
    }
}
