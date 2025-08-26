@echo off
for %%f in (*.mp3) do (
    ffmpeg -y -i "%%f" -ac 1 "%%f"
)