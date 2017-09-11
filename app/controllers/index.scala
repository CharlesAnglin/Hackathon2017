package controllers

import play.api.mvc.{Action, Controller}
import services.Rekog

class index extends Controller with Rekog {

  def indexFaces = Action {
    val files = List(
      "alex_fisher",
      "bal_phandal",
      "ben_cosford",
      "chandra_shekar_reddy_andem",
      "charlie_anglin",
      "dave_haigh",
      "duane_miles",
      "eddie_leeper",
      "james_rees",
      "james_whiteley",
      "john_eccleston",
      "jon_dent",
      "kieran_puaar",
      "kim_walker",
      "lee_kowalkowski",
      "luke_roberts",
      "melinda_marsh",
      "nathan_wood",
      "nathen_wilkinson",
      "pete_newman",
      "sarah_caswell",
      "sean_corrigan",
      "sean_evans",
      "toria_law",
      "tosin_ogunrinde",
      "tracey_harley",
      "wayne_ellis",
      "william_lea",
      "yen-kee_miu",
      "zabe_aziz"
    )
    val a = files.map(file => indexFace(file+".PNG",file))
    Ok("All faces indexed")
  }

}
