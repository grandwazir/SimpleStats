SimpleStats - Simple minecraft server statistics
====================================

SimpleStats is a plugin for the Minecraft wrapper [Bukkit](http://bukkit.org/) that allows administrators to track and log various metrics on their server to a database for further analysis. This could be used to create average player graphs, estimate the maximum number of player you server can comfortably hold or to help troubleshoot performance problems.

## Features

- Calcuate the number of ticks per second over a configurable interval
- Count the number of players on your server (triggered when a player joins or leaves)
- Track how much memory is being used and is available to the server perodicially.
- All statistics are logged to the database.

## Requirements

- [Bukkit Persistence](https://github.com/grandwazir/SimpleStats/wiki/database) needs to be configured in bukkit.yml

## Installation

### Ensure you are using the latest recommended build.

Before installing, you need to make sure you are running at least the latest [recommended build](http://dl.bukkit.org/latest-rb/craftbukkit.jar) for Bukkit. Support is only given for problems when using a recommended build. This does not mean that the plugin will not work on other versions of Bukkit, the likelihood is it will, but it is not supported.

### Getting SimpleStats

The best way to install SimpleStats is to use the [symbolic link](http://repository.james.richardson.name/symbolic/SimpleStats.jar) to the latest version. This link always points to the latest version of SimpleStats, so is safe to use in scripts or update plugins. Additionally you can to use the [RSS feed](http://dev.bukkit.org/server-mods/SimpleStats/files.rss) provided by BukkitDev as this also includes a version changelog.
    
Alternatively [older versions](http://repository.james.richardson.name/releases/name/richardson/james/bukkit/simplestats/) are available as well, however they are not supported. If you are forced to use an older version for whatever reason, please let me know why by [opening a issue](https://github.com/grandwazir/SimpleStats/issues/new) on GitHub.

## Configuration

### Read the documentation

All documentation for SimpleStats is available on the [GitHub wiki](https://github.com/grandwazir/SimpleStats/wiki), including [example usage](https://github.com/grandwazir/SimpleStats/wiki/Instructions).

