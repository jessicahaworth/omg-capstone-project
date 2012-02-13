dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
/*
dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "grails"
	password = "server"
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}

// environment specific settings
environments {
	development {
		dataSource {
			// one of 'create', 'create-drop','update'
			dbCreate = "create-drop"
			// NOTE: the JDBC connection string should be
			// all on the same line.
			url = "jdbc:mysql://localhost:3306/racetrack_dev?autoreconnect=true"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/racetrack_dev?autoreconnect=true"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:mysql://localhost:3306/racetrack_dev?autoreconnect=true"
		}
	}
}*/