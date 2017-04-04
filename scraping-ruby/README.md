### Prerequisities
1. Install ruby & nokogiri gem

### How to run
```$ ruby maven_spider.rb```

### DONE
* Printing only libraries 
* Remove 10K limit and scrape full
* Add instrumentation meta-data to output like (num_deps_found, time_taken_in_mins etc.,)

### NEXT

#### MVP
* Save dependencies found into a text file (test it on 1K limit)
* Write sample js/jquery to implement a suggest-box based html page (assuming all deps are in memory on client-side)
* Setup webserver and rework suggest box to get suggestions over ajax
* Implement 3 end points (1) Big search box with auto-suggest and show filtered deps (2) View all
* Setup cronjob to run every 15 mins and update the data

#### V1
* Move backend database into mongodb
* Setup elastic search

#### V2
* Use react/redux or angular on FE
* Move backend to nodejs
