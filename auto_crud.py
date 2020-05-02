__author__ = "Dhiraj Badu"

import argparse
import os,sys

parser = argparse.ArgumentParser(description='Auto Crud')
parser.add_argument('-p', dest='entity_package', help='entity package name')
parser.add_argument('-e', dest='entity_name', help='entity name')
args = parser.parse_args()

entity_package = args.entity_package
entity_name = args.entity_name

DIR_SEPERATOR = "/"
BASE =  os.getcwd()
ENTITY_BASE_PATH = BASE + "/src/main/java/com/realestate/re/service/core/"
CONTROLLER_BASE_PATH = BASE + "/src/main/java/com/realestate/re/service/web/controller/"
TEMPLATE_BASE_PATH = BASE + "/src/main/resources/automation/"
STRINGCONSTANTS_PATH = BASE + "/src/main/java/com/realestate/re/service/common/constants/StringConstants.java"
REPO_TEMPLATE = "repository_template.txt"
REPO_FILE_NAME = entity_name + "Repository.java"
DTO_TEMPLATE = "dto_template.txt"
DTO_FILE_NAME = entity_name +"Dto.java"
CONVERTER_TEMPLATE = "converter_template.txt"
CONVERTER_FILE_NAME = entity_name +"Converter.java"
SERVICE_TEMPLATE = "service_template.txt"
SERVICE_FILE_NAME = entity_name +"Service.java"
SERVICEIMPL_TEMPLATE = "serviceimpl_template.txt"
SERVICEIMPL_FILE_NAME = entity_name +"ServiceImpl.java"
ERROR_TEMPLATE = "error_template.txt"
ERROR_FILE_NAME = entity_name +"Error.java"
VALIDATION_TEMPLATE = "validation_template.txt"
VALIDATION_FILE_NAME = entity_name +"Validation.java"
CONTROLLER_TEMPLATE = "controller_template.txt"
CONTROLLER_FILE_NAME = entity_name +"Controller.java"
FILTER_TEMPLATE = "filter_template.txt"
FILTER_FILE_NAME = entity_name +"Filter.java"
RESTCONTROLLER_TEMPLATE = "restcontroller_template.txt"
RESTCONTROLLER_FILE_NAME = entity_name +"RestController.java"

def runCommand(bashCommand):
    os.system(bashCommand)

def addToGit(file):
    #print ("git add " + file)
    runCommand("git add " + file)

def readFileAndGetLines(path):
    file = open(path, "r")
    lines = file.readlines()
    return lines

def writeFile(path , contentList):
    file = open(path, "w")
    file.write(contentList)
    file.close()
    addToGit(path)


def repoGenerator():
    repoLine = readFileAndGetLines(TEMPLATE_BASE_PATH+REPO_TEMPLATE)
    resultRepo = ""
    for str in repoLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        resultRepo = resultRepo + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR+ REPO_FILE_NAME , resultRepo)
    print (REPO_FILE_NAME + " generated successfully")

def dtoGenerator():
    dtoLine = readFileAndGetLines(TEMPLATE_BASE_PATH+DTO_TEMPLATE)
    resultDto = ""
    for str in dtoLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        resultDto = resultDto + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + DTO_FILE_NAME , resultDto)
    print (DTO_FILE_NAME + " generated successfully")

def errorGenerator():
    errorLine = readFileAndGetLines(TEMPLATE_BASE_PATH+ERROR_TEMPLATE)
    resultError = ""
    for str in errorLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        resultError = resultError + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + ERROR_FILE_NAME , resultError)
    print (ERROR_FILE_NAME + " generated successfully")

def converterGenerator():
    converterLine = readFileAndGetLines(TEMPLATE_BASE_PATH+CONVERTER_TEMPLATE)
    resultConverter = ""
    for str in converterLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        resultConverter = resultConverter + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + CONVERTER_FILE_NAME , resultConverter)
    print (CONVERTER_FILE_NAME + " generated successfully")

def serviceGenerator():
    serviceLine = readFileAndGetLines(TEMPLATE_BASE_PATH+SERVICE_TEMPLATE)
    resultService = ""
    for str in serviceLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        resultService = resultService + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + SERVICE_FILE_NAME , resultService)
    print (SERVICE_FILE_NAME + " generated successfully")

def serviceImplGenerator():
    serviceImplLine = readFileAndGetLines(TEMPLATE_BASE_PATH+SERVICEIMPL_TEMPLATE)
    resultServiceImpl = ""
    for str in serviceImplLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        str = str.replace("{K-entityname}" , toCamelCase(entity_name))
        resultServiceImpl = resultServiceImpl + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + SERVICEIMPL_FILE_NAME , resultServiceImpl)
    print (SERVICEIMPL_FILE_NAME + " generated successfully")

def ValidationGenerator():
    validationLine = readFileAndGetLines(TEMPLATE_BASE_PATH+VALIDATION_TEMPLATE)
    resultValidation = ""
    for str in validationLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        str = str.replace("{K-entityname}" , toCamelCase(entity_name))
        resultValidation = resultValidation + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + VALIDATION_FILE_NAME , resultValidation)
    print (VALIDATION_FILE_NAME + " generated successfully")

def ControllerGenerator():
    controllerLine = readFileAndGetLines(TEMPLATE_BASE_PATH+CONTROLLER_TEMPLATE)
    resultController = ""
    for str in controllerLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        str = str.replace("{K-entityname}" , toCamelCase(entity_name))
        str = str.replace("{L-entityname}" , toLower(entity_name))
        str = str.replace("{U-entityname}" , toUpper(entity_name))
        resultController = resultController + str
    writeFile(CONTROLLER_BASE_PATH + CONTROLLER_FILE_NAME , resultController)
    print (CONTROLLER_FILE_NAME + " generated successfully")

def filterGenerator():
    filterLine = readFileAndGetLines(TEMPLATE_BASE_PATH+FILTER_TEMPLATE)
    resultFilter = ""
    for str in filterLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        resultFilter = resultFilter + str
    writeFile(ENTITY_BASE_PATH+ entity_package + DIR_SEPERATOR + FILTER_FILE_NAME , resultFilter)
    print (FILTER_FILE_NAME + " generated successfully")

def RestControllerGenerator():
    restcontrollerLine = readFileAndGetLines(TEMPLATE_BASE_PATH+RESTCONTROLLER_TEMPLATE)
    resultRestController = ""
    for str in restcontrollerLine:
        str = str.replace("{packagename}" , entity_package)
        str = str.replace("{entityname}" , entity_name)
        str = str.replace("{K-entityname}" , toCamelCase(entity_name))
        str = str.replace("{L-entityname}" , toLower(entity_name))
        str = str.replace("{U-entityname}" , toUpper(entity_name))
        resultRestController = resultRestController + str
    writeFile(CONTROLLER_BASE_PATH + DIR_SEPERATOR + "restcontroller" + DIR_SEPERATOR + RESTCONTROLLER_FILE_NAME , resultRestController)
    print (RESTCONTROLLER_FILE_NAME + " generated successfully")


def UpdateStringContants():
    stringContantsLine = readFileAndGetLines(STRINGCONSTANTS_PATH)

    stringContantsResult = ""
    for str in stringContantsLine:
        if "}" in str:
            break
        stringContantsResult = stringContantsResult + str

    stringContantsResult = stringContantsResult + "\n \t public static final String "+toUpper(entity_name)+"_ERROR = \""+toLower(entity_name)+"Error\";"
    stringContantsResult = stringContantsResult + "\n \t public static final String "+toUpper(entity_name)+"_LIST = \""+toLower(entity_name)+"List\";"
    stringContantsResult = stringContantsResult + "\n \t public static final String "+toUpper(entity_name)+" = \""+toLower(entity_name)+"\";"
    stringContantsResult = stringContantsResult + "\n }"

    writeFile(STRINGCONSTANTS_PATH , stringContantsResult)

def toCamelCase(value):
    value = value[0:1].lower() + value[1:len(value)]
    return value

def toLower(value):
    value = value.lower()
    return value

def toUpper(value):
    value = value.upper()
    return value

def dirExist(path):
    return os.path.isdir(path)
def fileExist(path):
    return os.path.exists(path)

def main():
    if (dirExist(ENTITY_BASE_PATH+entity_package) is False):
        print ("provided package not found ie. " + entity_package)
        sys.exit()
    elif(fileExist(ENTITY_BASE_PATH+entity_package+DIR_SEPERATOR+entity_name+".java") is False):
        print ("provided entity not found on " +entity_package+ " package ie. " + entity_name)
        sys.exit()
    repoGenerator()
    dtoGenerator()
    converterGenerator()
    serviceGenerator()
    serviceImplGenerator()
    errorGenerator()
    ValidationGenerator()
    ControllerGenerator()
    UpdateStringContants()
    filterGenerator()
    RestControllerGenerator()


if __name__ == '__main__':
    main()