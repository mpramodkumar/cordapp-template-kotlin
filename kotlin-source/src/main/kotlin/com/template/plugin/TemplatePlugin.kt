package com.template.plugin

import co.paralleluniverse.fibers.Suspendable
import net.corda.client.rpc.notUsed
import net.corda.core.contracts.*
import net.corda.core.crypto.Party
import net.corda.core.crypto.SecureHash
import net.corda.core.flows.FlowLogic
import net.corda.core.getOrThrow
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.node.CordaPluginRegistry
import net.corda.core.node.PluginServiceHub
import net.corda.core.transactions.SignedTransaction
import net.corda.core.utilities.ProgressTracker
import net.corda.flows.FinalityFlow
import rx.Observable
import java.security.PublicKey
import java.util.function.Function
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import com.template.api.TemplateApi
import com.template.flow.TemplateFlow

class TemplatePlugin : CordaPluginRegistry() {
   override val webApis = listOf(Function(::TemplateApi))
    override val requiredFlows = mapOf(TemplateFlow::class.java.name to setOf(Party::class.java.name))
    override val servicePlugins: List<Function<PluginServiceHub, out Any>> = listOf()
    override val staticServeDirs = mapOf("template" to javaClass.classLoader.getResource("templateWeb").toExternalForm())
}