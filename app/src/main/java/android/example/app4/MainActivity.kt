
package android.example.app4

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Adapter.OnLongClickListener{

//VARIABLEN DEFINIEREN



    private val exampleList = generateDummyList(22)
    private val adapter = Adapter(exampleList, this)

    private lateinit var Seite2Button: Button
    private lateinit var listRecyclerView: RecyclerView
    //private lateinit var buttonchange: Button

//ONCREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    //RECYCLERVIEW
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

    //FINDVIEWBYID
        Seite2Button = findViewById(R.id.New_Activity)
        listRecyclerView = findViewById(R.id.recycler_view)
        //buttonchange = findViewById(R.id.button_change)

    //IMPLEMENTRIERUNG

   /* buttonchange.setOnClickListener{
    }*/

    Seite2Button.setOnClickListener {
        val intent = Intent(this, FilesActivity::class.java)
        startActivity(intent)
    }


    }

//WEITERE KLASSEN

    override fun onLongClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]

        //clickedItem.Deutsch = "Clicked"
        //adapter.notifyItemChanged(position)

        exampleList.removeAt(position)
        adapter.notifyItemRemoved(position)

    }

   private fun generateDummyList(size: Int): ArrayList<Item> {
        val list = ArrayList<Item>()
        for (i in 0 until size) {

            val item = Item("Deutsch - ${i+1}", "Englisch")
            list += item
        }
        return list
    }

    //MENÜ KLASSE
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.open_menu->{

                val intent = Intent(this, InputActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*fun insertItem(view: View) {

        val a = intent.getStringExtra("inputText1")

        val index = 0

        val newItem = Item(
            "de $a",
            "en")

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }*/

    public fun insertItem(view: Editable) {                             //Das will ich aufrufen und da variable weitergeben
        //val a = intent.getStringExtra("inputText1")

        //String inputText1 = intent.getStringExtra("inputText1")
        //string inputText1 = intent.getStringExtra("inputText1")
        val inputText1 = intent.getStringExtra("EXTRA_SESSION_ID")


        //val a = "kp"
        //val b = InputActivity.inputText1



        val index = 0

        val newItem = Item(
            "de $inputText1",
            "en")

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)    }

}
