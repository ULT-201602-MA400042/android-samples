

package androidcourse.databaseexample.Models;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Example of migrating a Realm file from version 0 (initial version) to its last version (version 1).
 */
public class Migration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {

        // Access the Realm schema in order to create, modify or delete classes and their fields.
        RealmSchema schema = realm.getSchema();

        // Migrate from version 0 to version 1
        if (oldVersion == 0) {
            RealmObjectSchema contactSchema = schema.get("Contact");

            // add address
            contactSchema.addField("address", String.class);
            oldVersion++;
        }
    }
}