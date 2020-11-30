package com.example.minimoneybox.ui.main.useraccounts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skyscanner.mini_skyscanner.R
import com.skyscanner.mini_skyscanner.databinding.LayoutItineraryListItemBinding
import com.skyscanner.mini_skyscanner.models.Itinerary
import com.skyscanner.mini_skyscanner.models.Leg
import com.skyscanner.mini_skyscanner.util.DateUtils
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class FlightsRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var itineraries: List<Itinerary> = listOf()

    lateinit private var context: Context
    lateinit private var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        layoutInflater = LayoutInflater.from(context)
        val itemBinding = LayoutItineraryListItemBinding.inflate(layoutInflater, parent, false)
        return ItineraryViewHolder(itemBinding, layoutInflater, context)
    }

    override fun getItemCount(): Int {
        return itineraries.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itinerary = itineraries.get(position)
        val itineraryViewHolder = holder as ItineraryViewHolder
        itineraryViewHolder.bind(itinerary)
    }

    fun setItineraries(itineraries: List<Itinerary>) {
        this.itineraries = itineraries
        notifyDataSetChanged()
    }

    class ItineraryViewHolder(
        private val binding : LayoutItineraryListItemBinding,
        private val inflater: LayoutInflater,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            itinerary: Itinerary
        ) {
            binding.tvPrice.text = itinerary.price
            binding.tvAgent.text = "via %s".format(itinerary.agent)
            binding.llContainer.removeAllViews()
            itinerary.legs?.forEach {
                addLegs(it, binding.llContainer)
            }
        }

        private fun addLegs(
            leg: Leg?,
            container: LinearLayout
        ) {
            leg?.let {leg ->
                val layoutLegItem = inflater.inflate(R.layout.layout_leg_item, null)
                val ivAirline: ImageView = layoutLegItem.findViewById(R.id.iv_airline)
                val tvTimePeriod: TextView = layoutLegItem.findViewById(R.id.tv_time_period)
                val tvItineraryInfo: TextView = layoutLegItem.findViewById(R.id.tv_itinerary_info)
                val tvStops: TextView = layoutLegItem.findViewById(R.id.tv_stops)
                val tvDuration: TextView = layoutLegItem.findViewById(R.id.tv_duration)

                Glide.with(context).load("https://logos.skyscnr.com/images/airlines/small/%s.png".format(leg.airlineId)).into(ivAirline);
                tvTimePeriod.text = "%s - %s".format(DateUtils.formatTime(leg.departureTime), DateUtils.formatTime(leg.arrivalTime))
                tvItineraryInfo.text = "%s-%s, %s".format(leg.departureAirport, leg.arrivalAirport, leg.airlineName)
                if (leg.stops == 0) {
                    tvStops.text = "Direct"
                } else if (leg.stops == 1) {
                    tvStops.text = "%d stop".format(leg.stops)
                } else if (leg.stops == 0) {
                    tvStops.text = "%d stops".format(leg.stops)
                }
                val hour = leg.durationMins / 60
                val minute = leg.durationMins % 60
                if (hour == 0) {
                    tvDuration.text = "%dm".format(minute)
                } else {
                    tvDuration.text = "%dh %dm".format(hour, minute)
                }

                container.addView(layoutLegItem)
            }
        }
    }
}