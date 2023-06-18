# Load SQL Server cmdlets, it´s like import in java.
Clear-Host

Import-Module SqlServer

#wsl -u root -d Ubuntu
wsl -u root -d Ubuntu --exec bash -c "podman start mssqlserver-2022 && exit;";

$Password = ConvertTo-SecureString "MSSQLServer.1234" -AsPlainText -Force;
$Password.MakeReadOnly();
$Credential = New-Object System.Management.Automation.PSCredential ("sa", $Password);

function BackupDatabase($DatabaseName) {
    Write-Host "Backup database $DatabaseName."
    # Set variables
    $ServerInstance = "127.0.0.1"
    $WslDockerContainerBackupDir = "/tmp"
    $BackupDir = "C:\Backup"
    $ArchiveDir = "C:\Backup\Archive"
    $Timestamp = Get-Date -Format "yyyyMMddTHH.mm.ss"
    $WslDockerContainerBackupFile = "${WslDockerContainerBackupDir}/${DatabaseName}_${Timestamp}.bak"
    $BackupFile = "${BackupDir}\${DatabaseName}_${Timestamp}.bak"
    $ArchiveFile = "${ArchiveDir}\${DatabaseName}_${Timestamp}.7z"
 
    # Create backup and archive directories if they don't exist
    if (!(Test-Path $BackupDir)) {
        New-Item -ItemType Directory -Path $BackupDir
    }
    if (!(Test-Path $ArchiveDir)) {
        New-Item -ItemType Directory -Path $ArchiveDir
    }
 
    # Backup the SQL Server database
    Backup-SqlDatabase -ServerInstance $ServerInstance -Database $DatabaseName -BackupFile $WslDockerContainerBackupFile -Credential $Credential
    Write-Host "Database backup completed successfully."

    Write-Host "Executingin WSL: podman cp mssqlserver-2022:${WslDockerContainerBackupFile} /mnt/c/Backup/"
    wsl -u root -d Ubuntu --exec bash -c "podman cp mssqlserver-2022:${WslDockerContainerBackupFile} /mnt/c/Backup/"
    Write-Host "Database backup copied successfully."

    wsl -u root -d Ubuntu --exec bash -c "podman exec -t -i mssqlserver-2022 /bin/bash -c 'rm ${WslDockerContainerBackupFile}'"
    Write-Host "Database backup deleted from container successfully."

 
    # Archive the backup using 7-Zip
    & "C:\Program Files\7-Zip\7z.exe" a -t7z $ArchiveFile $BackupFile
    Write-Host "Backup archived successfully."
 
    # Remove the original backup file
    Remove-Item $BackupFile
    Write-Host "Original backup file removed."
}

$Databases = Get-SqlInstance -Credential $Credential -ServerInstance "127.0.0.1" | Get-SqlDatabase

foreach ($Database in $Databases) {
    BackupDatabase($Database.Name.ToString())
}


