
#include "dbfunctions.h"
#include "dbimpl.h"
#include "sha256.h"

#include "rapidjson/document.h"
#include "rapidjson/writer.h"
#include "rapidjson/stringbuffer.h"

using namespace rapidjson;

namespace microdb {

    rapidjson::Value& indexableValue(Environment* env, const std::vector< Selector* >& args) {
        Value retval;
        
        if(args.size() >= 1) {
            Value& data = args[0]->select(env);
            if(data.IsNumber()) {
                std::string indexValue = IndexDataum::convert(data.GetDouble());
                retval.SetString(indexValue.data(), indexValue.size(), env->getGlobalAllocator());
            } else if(data.IsString()) {
                std::string indexValue = IndexDataum::convert(data.GetString(), data.GetStringLength());
                retval.SetString(indexValue.data(), indexValue.size(), env->getGlobalAllocator());
            }
            
        }
        
        return retval.Move();
        
    }
    
    rapidjson::Value& hash(Environment* env, const std::vector< Selector* >& args) {
        Value retval;
        
        if(args.size() >= 1) {
            StringBuffer keyBuffer;
            Writer<StringBuffer> keyWriter(keyBuffer);
            args[0]->select(env).Accept(keyWriter);
            
            std::string hashStr = sha256(keyBuffer.GetString());
            
            retval.SetString(hashStr.c_str(), hashStr.size(), env->getGlobalAllocator());
        } else {
            retval.SetNull();
        }
        
        return retval.Move();
    }
    
}
