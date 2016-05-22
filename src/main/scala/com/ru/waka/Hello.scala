package com.ru.waka

import java.io.File
import java.net.URL

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.Feed
import com.google.gdata.data.docs.SpreadsheetEntry
import com.google.gdata.data.spreadsheet.SpreadsheetFeed

import scala.collection.JavaConverters._

object Hello {
  private val jsonFactory = new JacksonFactory

  private val transport = GoogleNetHttpTransport.newTrustedTransport()

  private val service   = new SpreadsheetService(this.getClass.getName)

  private val scopes  = Seq(
    "https://www.googleapis.com/auth/drive.file",
    "https://www.googleapis.com/auth/userinfo.email",
    "https://www.googleapis.com/auth/userinfo.profile",
    "https://docs.google.com/feeds",
    "https://spreadsheets.google.com/feeds"
  ).asJava

  def main (args: Array[String] ) {
    val c = new GoogleCredential.Builder()
      .setTransport(transport)
      .setJsonFactory(jsonFactory)
      .setServiceAccountId("embulk-input-plugin@hubot-dark.iam.gserviceaccount.com")
      .setServiceAccountPrivateKeyFromP12File(new File("./hubot-dark-6e8b3857b0b0.p12"))
      .setServiceAccountScopes(scopes)
      .build()

    c.refreshToken()
    service.setOAuth2Credentials(c)
    val e = service.getFeed(new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full"), classOf[SpreadsheetFeed])
    println(e.getAuthors)
    println(e.getTitle.getPlainText)
  }
}



