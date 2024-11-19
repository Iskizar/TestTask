SELECT 
    r.familyName AS relativeFamilyName,
    r.givenName AS relativeGivenName,
    r.middleName AS relativeMiddleName,
    r.birthDate AS relativeBirthDate,
    d.contactRelationship AS relationship
FROM HPPersonDependant d
JOIN HPPersonGeneric r ON r.sysId = d.HPRelatedPersonSysId
JOIN HPPersonGeneric e ON e.sysId = d.HPPersonGenericSysId
WHERE e.personId = 'test';
