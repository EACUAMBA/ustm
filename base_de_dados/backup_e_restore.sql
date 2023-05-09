use car_wash;

BACKUP DATABASE [car_wash]
TO DISK = '/tmp/backup.bak'
   WITH FORMAT,
      MEDIANAME = 'SQLServerBackups',
      NAME = 'Full Backup of SQLTestDB';
GO;

-- podman exec -u 0 -t -i mssqlserver-2022 /bin/bash
--  podman cp mssqlserver-2022:/tmp/ ./Desktop
