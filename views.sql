CREATE MATERIALIZED VIEW accommodation_count_by_host AS
    SELECT host_id, COUNT(*) AS accommodation_count
    FROM accommodation
    GROUP BY host_id;


CREATE MATERIALIZED VIEW hosts_by_country AS
    SELECT host.country_id, COUNT(*) AS host_count
    FROM host
    GROUP BY host.country_id;

