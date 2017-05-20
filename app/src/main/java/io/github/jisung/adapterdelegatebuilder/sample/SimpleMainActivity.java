package io.github.jisung.adapterdelegatebuilder.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.jisung.adapterdelegatebuilder.SimpleListItemAdapterDelegate;
import io.github.jisung.adapterdelegatebuilder.SimpleViewBinder;
import io.github.jisung.adapterdelegatebuilder.SimpleViewHolder;
import io.github.jisung.adapterdelegatebuilder.ViewTypeChecker;
import io.github.jisung.adapterdelegatebuilder.sample.model.Advertisement;
import io.github.jisung.adapterdelegatebuilder.sample.model.Cat;
import io.github.jisung.adapterdelegatebuilder.sample.model.DisplayableItem;
import io.github.jisung.adapterdelegatebuilder.sample.model.Dog;
import io.github.jisung.adapterdelegatebuilder.sample.model.Gecko;
import io.github.jisung.adapterdelegatebuilder.sample.model.Snake;

public class SimpleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(io.github.jisung.adapterdelegatebuilder.sample.R.layout.activity_simple_main);

        RecyclerView rv = (RecyclerView) findViewById(io.github.jisung.adapterdelegatebuilder.sample.R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        SimpleMainAdapter adapter = new SimpleMainAdapter();
        adapter.setItems(getAnimals());
        rv.setAdapter(adapter);
    }

    private List<DisplayableItem> getAnimals() {
        List<DisplayableItem> animals = new ArrayList<>();

        animals.add(new Cat("American Curl"));
        animals.add(new Cat("Baliness"));
        animals.add(new Cat("Bengal"));
        animals.add(new Cat("Corat"));
        animals.add(new Cat("Manx"));
        animals.add(new Cat("Nebelung"));
        animals.add(new Dog("Aidi"));
        animals.add(new Dog("Chinook"));
        animals.add(new Dog("Appenzeller"));
        animals.add(new Dog("Collie"));
        animals.add(new Snake("Mub Adder", "Adder"));
        animals.add(new Snake("Texas Blind Snake", "Blind snake"));
        animals.add(new Snake("Tree Boa", "Boa"));
        animals.add(new Gecko("Fat-tailed", "Hemitheconyx"));
        animals.add(new Gecko("Stenodactylus", "Dune Gecko"));
        animals.add(new Gecko("Leopard Gecko", "Eublepharis"));
        animals.add(new Gecko("Madagascar Gecko", "Phelsuma"));
        animals.add(new Advertisement());
        animals.add(new Advertisement());
        animals.add(new Advertisement());
        animals.add(new Advertisement());
        animals.add(new Advertisement());

        Collections.shuffle(animals);
        return animals;
    }

    private static class SimpleMainAdapter extends ListDelegationAdapter<List<DisplayableItem>> {

        SimpleMainAdapter() {

            delegatesManager.addDelegate(
                    new SimpleListItemAdapterDelegate.Builder<Advertisement, DisplayableItem>()
                            .typeChecker(new ViewTypeChecker<DisplayableItem>() {
                                @Override
                                public boolean isForViewType(@NonNull DisplayableItem item) {
                                    return item instanceof Advertisement;
                                }
                            })
                            .viewLayout(R.layout.item_advertisement)
                            .build());

            delegatesManager.addDelegate(
                    new SimpleListItemAdapterDelegate.Builder<Cat, DisplayableItem>()
                            .typeChecker(new ViewTypeChecker<DisplayableItem>() {
                                @Override
                                public boolean isForViewType(@NonNull DisplayableItem item) {
                                    return item instanceof Cat;
                                }
                            })
                            .viewLayout(R.layout.item_cat)
                            .viewBinder(new SimpleViewBinder<Cat>() {
                                @Override
                                public void onBindViewHolder(SimpleViewHolder vh, @NonNull Cat cat) {
                                    vh.getView(R.id.name, TextView.class).setText(cat.getName());
                                }
                            })
                            .build());

            delegatesManager.addDelegate(
                    new SimpleListItemAdapterDelegate.Builder<Dog, DisplayableItem>()
                            .typeChecker(new ViewTypeChecker<DisplayableItem>() {
                                @Override
                                public boolean isForViewType(@NonNull DisplayableItem item) {
                                    return item instanceof Dog;
                                }
                            })
                            .viewLayout(R.layout.item_dog)
                            .viewBinder(new SimpleViewBinder<Dog>() {
                                @Override
                                public void onBindViewHolder(SimpleViewHolder vh, @NonNull Dog dog) {
                                    vh.getView(R.id.name, TextView.class).setText(dog.getName());
                                }
                            })
                            .build());

            delegatesManager.addDelegate(
                    new SimpleListItemAdapterDelegate.Builder<Gecko, DisplayableItem>()
                            .typeChecker(new ViewTypeChecker<DisplayableItem>() {
                                @Override
                                public boolean isForViewType(@NonNull DisplayableItem item) {
                                    return item instanceof Gecko;
                                }
                            })
                            .viewLayout(R.layout.item_gecko)
                            .viewBinder(new SimpleViewBinder<Gecko>() {
                                @Override
                                public void onBindViewHolder(SimpleViewHolder vh, @NonNull Gecko gecko) {
                                    vh.getView(R.id.name, TextView.class).setText(gecko.getName());
                                    vh.getView(R.id.race, TextView.class).setText(gecko.getRace());
                                }
                            })
                            .build());

            delegatesManager.addDelegate(
                    new SimpleListItemAdapterDelegate.Builder<Snake, DisplayableItem>()
                            .typeChecker(new ViewTypeChecker<DisplayableItem>() {
                                @Override
                                public boolean isForViewType(@NonNull DisplayableItem item) {
                                    return item instanceof Snake;
                                }
                            })
                            .viewLayout(R.layout.item_snake)
                            .viewBinder(new SimpleViewBinder<Snake>() {
                                @Override
                                public void onBindViewHolder(SimpleViewHolder vh, @NonNull Snake snake) {
                                    vh.getView(R.id.name, TextView.class).setText(snake.getName());
                                    vh.getView(R.id.race, TextView.class).setText(snake.getRace());
                                }
                            })
                            .build());

            delegatesManager.setFallbackDelegate(
                    new SimpleListItemAdapterDelegate.Builder<DisplayableItem, DisplayableItem>()
                            .viewLayout(android.R.layout.simple_list_item_1)
                            .viewBinder(new SimpleViewBinder<DisplayableItem>() {
                                @Override
                                public void onBindViewHolder(SimpleViewHolder vh, @NonNull DisplayableItem item) {
                                    vh.getView(android.R.id.text1, TextView.class).setText(item.toString());
                                }
                            })
                            .build());
        }

        @Override
        public void setItems(List<DisplayableItem> items) {
            super.setItems(items);
            notifyDataSetChanged();
        }
    }
}
