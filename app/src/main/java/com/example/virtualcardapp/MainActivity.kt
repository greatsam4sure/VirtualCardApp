package com.example.virtualcardapp

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.fidel.sdk.Fidel
import com.fidel.sdk.LinkResult
import com.fidel.sdk.LinkResultError
import com.fidel.sdk.data.abstraction.FidelCardLinkingObserver
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.EnumSet


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpFidelSDK()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Fidel.present(this@MainActivity)
        }

        getCards()

    }

    private fun getCards() {
        val cardAPIInterface: CardAPIInterface =
            APIClient.buildService(CardAPIInterface::class.java)
        val requestCall: Call<CardList> = cardAPIInterface.getCards(Constants.programID)
        requestCall.enqueue(object : Callback<CardList?> {
            override fun onResponse(
                call: Call<CardList?>,
                response: Response<CardList?>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d("RET", data.toString())
                }
            }

            override fun onFailure(call: Call<CardList?>, t: Throwable) {
                Log.d("RET", "An error has occurred")
            }
        })


    }


    private fun setUpFidelSDK() {
        Fidel.apiKey = "pk_test_092593c3-2b38-488e-893d-ef25fba649b1"
        Fidel.programId = "d401c50c-5012-4ee8-b726-72e70b89eb49"

        Fidel.bannerImage = BitmapFactory.decodeResource(resources, R.drawable.pix)

        Fidel.autoScan = false

        Fidel.allowedCountries = Fidel.Country.values()

        Fidel.defaultSelectedCountry = Fidel.Country.UNITED_STATES

        Fidel.companyName = "Diligent Academy Internation"

        Fidel.privacyURL = "https://www.fidel.uk/"

        Fidel.termsConditionsURL = "https://fidel.uk/docs/"

        Fidel.programName = "Virtual Card"

        Fidel.supportedCardSchemes = EnumSet.of(
            Fidel.CardScheme.AMERICAN_EXPRESS,
            Fidel.CardScheme.VISA,
            Fidel.CardScheme.MASTERCARD
        )

        Fidel.deleteInstructions = "going to your account settings"

        val jsonMeta = JSONObject()
        try {
            jsonMeta.put("id", "this-is-the-metadata-id")
            jsonMeta.put("customKey1", "customValue1")
            jsonMeta.put("customKey2", "customValue2")
        } catch (e: JSONException) {
            val exceptionMessage = e.localizedMessage
            if (exceptionMessage != null) {
                Log.e(Fidel.FIDEL_DEBUG_TAG, exceptionMessage)
            }
        }
        Fidel.metaData = jsonMeta

        Fidel.setCardLinkingObserver(object : FidelCardLinkingObserver {
            override fun onCardLinkingFailed(p0: LinkResultError?) {
                Log.e(Fidel.FIDEL_DEBUG_TAG, "Error message = " + p0?.message)
            }

            override fun onCardLinkingSucceeded(p0: LinkResult?) {
                Log.d(Fidel.FIDEL_DEBUG_TAG, "The link ID = " + p0?.id)
                Log.d(Fidel.FIDEL_DEBUG_TAG, "Card is successfully link")


            }
        })

    }


}