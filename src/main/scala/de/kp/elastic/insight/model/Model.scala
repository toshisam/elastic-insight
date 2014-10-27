package de.kp.elastic.insight.model
/* Copyright (c) 2014 Dr. Krusche & Partner PartG
* 
* This file is part of the Elastic-Insight project
* (https://github.com/skrusche63/elastic-insight).
* 
* Elastic-Insight is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* 
* Elastic-Insight is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with
* Elastic-Insight. 
* 
* If not, see <http://www.gnu.org/licenses/>.
*/

import org.json4s._

import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read,write}

case class ServiceRequest(
  service:String,task:String,data:Map[String,String]
)

case class ServiceResponse(
  service:String,task:String,data:Map[String,String],status:String
)

case class Pattern(
  support:Int,itemsets:List[List[Int]])

case class Patterns(items:List[Pattern])

case class Rule (
  antecedent:List[Int],consequent:List[Int],support:Int,confidence:Double)

case class Rules(items:List[Rule])

object ResponseStatus {
  
  val FAILURE:String = "failure"
  val SUCCESS:String = "success"
    
}

object Serializer {
    
  implicit val formats = Serialization.formats(NoTypeHints)

  def serializePatterns(patterns:Patterns):String = write(patterns) 
  def deserializePatterns(patterns:String):Patterns = read[Patterns](patterns)
  
  def serializeRules(rules:Rules):String = write(rules)  
  def deserializeRules(rules:String):Rules = read[Rules](rules)
  
  def serializeRequest(request:ServiceRequest):String = write(request)
  
}

object Services {
  	
	/** 
	The Association Analysis Service discovers hidden relations in large-scale databases; 
	the respective result may be used by find hidden aspects about customer and products 
	and may help e.g. marketers to improve targeting. 
	*/
	val ASSOCIATION:String = "association"
	/** 
	The Context-Aware Analysis Service leverages context-sensitive information to e.g. provide
	personalized recommendations.
	*/
	val CONTEXT:String = "context"	
	/** 
	The Decision Analysis Service predicts the best decisions among multiple courses of action 
	and identifies their decisive factors. 
	*/
	val DECISION:String = "decision"
	/** 
	The Intent Recognition Service uncover the intents of human behavior and delivers the ultimate 
	customer understanding. 
	*/
	val INTENT:String = "intent"
	/**
	The Outlier Detection Service finds anomalies in large-scale datasets and human behavior for 
	advanced risk reduction.  
	*/
	val OUTLIER:String ="outlier"
    /**
    The Series Service detects frequent patterns and rules in activity sequences; the respective
    results may be used to predict pre- and post-behavior with respect to a specific event.
     */
	val SERIES:String = "series"
    /** 
    The Similarity Service finds relevant similarities in dynamic activity sequences and identifies 
    customers by their journeys.
    */
	val SIMILARITY:String = "similarity"
    /**
    The Social Analysis Service determines and leverages actual trends from social media platforms 
    in real-time.
    */
	val SOCIAL:String = "social"
    /**
    The Text Analysis Service language-agnostic semantic concept detection and prediction for 
    semantic targeting.
    */
	val TEXT:String = "text"
    /**
    The MetaService collects XML based metadata description that are used by the respective engines 
    to access supported data sources; e.g. for the decision service a metadata description specifies 
    which fields have to be taken into account and which of them are categorical or numerical fields.
    */
	val META:String = "meta"

	private val services = List(
	    ASSOCIATION,CONTEXT,DECISION,INTENT,OUTLIER,SERIES,SIMILARITY,SOCIAL,TEXT
	)
	
	def isService(service:String):Boolean = services.contains(service)
	
}
