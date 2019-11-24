package com.example.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_row.*

class DrinkAdapter(val items: Array<Drink>) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>()
{
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView //To change initializer of created properties use File | Settings | File Templates.

        fun bindAndVersion(drink: Drink) {
            //la fonction qui permet de lier les données à la vue
            with(drink) {
                //itemView.andVersionTxt.text = "$name"
                name.text = strDrink
                description.text = strDrinkThumb
                t.text =idDrink
            }
        }

    }
    override fun getItemCount(): Int = items.size //la fonction retournant le nombre d’éléments de la liste :


    //la fonction retournant la visualisation d’une ligne de la liste :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return ViewHolder(lineView)
    }

    //la fonction s’occupant de charger les données dans la vue :
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindAndVersion(items[position])
    }



}