create table group (id uuid PRIMARY KEY, name text, description text, type text, region text, position text, beliefs text);

create table person(id uuid PRIMARY KEY, firstName text, lastName text, age NUMERIC, gender text);

create table implication(id uuid PRIMARY KEY , status text, group uuid REFERENCES group(id), person uuid REFERENCES person(id));