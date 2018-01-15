package org.springframework.contrib.gae.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RefsTest extends ObjectifyTest {
    @Test
    public void ref()  {
        TestStringEntity entity = new TestStringEntity("id");

        assertThat(Refs.ref(entity)).isEqualTo(Ref.create(entity));
    }

    @Test
    public void ref_willReturnNull_whenEntityIsNull()  {
        assertThat(Refs.ref((TestStringEntity) null))
                .isNull();
    }

    @Test
    public void refCollection()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");

        assertThat(Refs.ref(
                Arrays.asList(
                        a, b, null, c
                )
        )).containsExactly(
                Ref.create(a),
                Ref.create(b),
                null,
                Ref.create(c)
        );
    }

    @Test
    public void refVarargs()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");

        assertThat(Refs.ref(
                a, b, null, c
        )).containsExactly(
                Ref.create(a),
                Ref.create(b),
                null,
                Ref.create(c)
        );
    }

    @Test
    public void refKey()  {
        TestStringEntity entity = new TestStringEntity("id");

        assertThat(Refs.ref(Key.create(entity)))
                .isEqualTo(Ref.create(entity));
    }

    @Test
    public void refKey_willReturnNull_whenKeyIsNull()  {
        assertThat(Refs.ref((Key<?>) null))
                .isNull();
    }

    @Test
    public void refKeysCollection()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");

        assertThat(Refs.refKeys(
                Arrays.asList(
                        Key.create(a),
                        Key.create(b),
                        null,
                        Key.create(c)
                )
        )).containsExactly(
                Ref.create(a),
                Ref.create(b),
                null,
                Ref.create(c)
        );
    }

    @Test
    public void refKeysVarargs()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");

        assertThat(Refs.refKeys(
                Key.create(a),
                Key.create(b),
                null,
                Key.create(c)
        )).containsExactly(
                Ref.create(a),
                Ref.create(b),
                null,
                Ref.create(c)
        );
    }

    @Test
    public void key()  {
        TestStringEntity entity = new TestStringEntity("id");

        assertThat(Refs.key(entity))
                .isEqualTo(Key.create(entity));
    }

    @Test
    public void key_willReturnNull_whenKeyIsNull()  {
        assertThat(Refs.key((TestStringEntity) null))
                .isNull();
    }

    @Test
    public void keyCollection()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");

        assertThat(Refs.key(
                Arrays.asList(a, b, null, c)
        )).containsExactly(
                Key.create(a),
                Key.create(b),
                null,
                Key.create(c)
        );
    }

    @Test
    public void keyVarargs()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");

        assertThat(Refs.key(
                a, b, null, c
        )).containsExactly(
                Key.create(a),
                Key.create(b),
                null,
                Key.create(c)
        );
    }

    @Test
    public void deref()  {
        TestStringEntity entity = new TestStringEntity("id");
        save(entity);

        Ref<TestStringEntity> ref = Ref.create(entity);

        assertThat(
                Refs.deref(ref)
        ).isEqualTo(entity);
    }

    @Test
    public void derefCollection()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");
        save(a, b, c);

        assertThat(Refs.deref(
                Arrays.asList(
                        Ref.create(a),
                        Ref.create(b),
                        null,
                        Ref.create(c)
                )
        )).containsExactly(
                a, b, null, c
        );
    }

    @Test
    public void derefVarargs()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");
        save(a, b, c);

        assertThat(Refs.deref(
                Ref.create(a),
                Ref.create(b),
                null,
                Ref.create(c)
        )).containsExactly(
                a, b, null, c
        );
    }

    @Test
    public void load()  {
        TestStringEntity entity = new TestStringEntity("id");
        save(entity);

        Key<TestStringEntity> key = Key.create(entity);

        assertThat(
                Refs.load(key)
        ).isEqualTo(entity);
    }

    @Test
    public void load_willReturnNull_whenKeyIsNull()  {
        assertThat(
                Refs.load((Key<?>) null)
        ).isNull();
    }

    @Test
    public void loadCollection()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");
        save(a, b, c);

        assertThat(Refs.load(
                Arrays.asList(
                        Key.create(a),
                        Key.create(b),
                        null,
                        Key.create(c)
                )
        )).containsExactly(
                a, b, null, c
        );
    }

    @Test
    public void loadVarargs()  {
        TestStringEntity a = new TestStringEntity("a");
        TestStringEntity b = new TestStringEntity("b");
        TestStringEntity c = new TestStringEntity("c");
        save(a, b, c);

        assertThat(Refs.load(
                Key.create(a),
                Key.create(b),
                null,
                Key.create(c)
        )).containsExactly(
                a, b, null, c
        );
    }
}
