create table groups (id UUID PRIMARY KEY, name text, description text, type text, region text, position text, beliefs text);

create table person(id UUID PRIMARY KEY, firstName text, lastName text, age NUMERIC, gender text);

create table implication(id UUID PRIMARY KEY , status text, group_id UUID REFERENCES groups(id), person_id UUID REFERENCES person(id));